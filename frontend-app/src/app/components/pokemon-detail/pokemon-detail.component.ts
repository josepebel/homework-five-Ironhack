import { Component, Input, OnInit } from '@angular/core';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokemon-detail',
  templateUrl: './pokemon-detail.component.html',
  styleUrls: ['./pokemon-detail.component.css']
})
export class PokemonDetailComponent implements OnInit {
  pokemons: any[] = [];
  
  @Input()
  pokemon: any;

  constructor(private PokemonService: PokemonService) {
    
  }

  ngOnInit(): void {
    console.log(this.pokemon)
  }

}
