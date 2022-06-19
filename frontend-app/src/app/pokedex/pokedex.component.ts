import { Component, OnInit } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';

@Component({
  selector: 'app-pokedex',
  templateUrl: './pokedex.component.html',
  styleUrls: ['./pokedex.component.css']
})
export class PokedexComponent implements OnInit {

  selectedPokemon: any;

  constructor(private PokemonService: PokemonService) {
   }

  ngOnInit(): void {
  }

  onSelectedPokemon(event: any): void{
    this.selectedPokemon = event;
  }

}