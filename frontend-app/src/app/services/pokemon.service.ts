import { Trainer } from './../models/trainer.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  constructor(private http: HttpClient) { }

  //Obtiene pokemon
  getPokemons(limit:number, offset:number){
    return this.http.get(`https://pokeapi.co/api/v2/pokemon?limit=${limit}&offset=${offset}`);
  }

  getPokemonDetails(name: string){
    return this.http.get(`https://pokeapi.co/api/v2/pokemon/${name}`);
  }

  getAllPokemons(){
    return this.http.get(`https://pokeapi.co/api/v2/pokemon?limit=1118`);
  }

  readonly baseUrl = 'http://localhost:8080';

  getTeam(trainerName: string){
    return this.http.get(this.baseUrl + `/pokemons/${trainerName}`);
  }

  deletePokemon(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + '/pokemons/' + id);
  }

  addPokemon(trainer: string, pokemon: any): Observable<any> {

    const body = {
      name: pokemon.name,
      imageUrl: pokemon.imageUrl,
      typeList: pokemon.typeList,
      statsList: pokemon.statsList
    }

    return this.http.post<any>(this.baseUrl + `/pokemons/${trainer}`, body);
  }

}
