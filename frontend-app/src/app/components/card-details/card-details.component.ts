import { PokemonService } from './../../services/pokemon.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {

  pokemons: any[] = [];

  @Input()
  pokemon: any;

  constructor(private PokemonService: PokemonService) { }

  ngOnInit(): void {

  }

}
