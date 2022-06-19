import { Component, OnInit} from '@angular/core';
import { PokemonService } from 'src/app/services/pokemon.service';
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-pokemon-list',
  templateUrl: './pokemon-list.component.html',
  styleUrls: ['./pokemon-list.component.css']
})
export class PokemonListComponent implements OnInit {
  pokemons: any[] = [];
  page = 1;
  itemsPerPage = 20;
  selectedPokemon : any;
  query: string = "";
  total: number = 0;

  @Output() selectPokemon = new EventEmitter<string>();

  constructor(
    private PokemonService: PokemonService
  ) { }

  ngOnInit(): void {
    this.getPokemons();
  }

  getPokemons() {
    this.PokemonService.getPokemons(this.itemsPerPage, (this.page - 1)*this.itemsPerPage)
      .subscribe((response: any)=>{
        this.total = response.count;
        response.results.forEach((result: { name: string; }) => {    
          this.PokemonService.getPokemonDetails(result.name)
            .subscribe((uniqResponse: any) =>{
              this.pokemons.push(uniqResponse);
              this.pokemons.sort(function(a, b) {
                return a.id - b.id;
              });  
            });
        });
      });
  }

}