import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TrainersComponent } from './components/trainers/trainers.component';
import { TrainerItemComponent } from './components/trainer-item/trainer-item.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { PokemonDetailComponent } from './components/pokemon-detail/pokemon-detail.component';
import { PokemonListComponent } from './components/pokemon-list/pokemon-list.component';
import { PokedexComponent } from './pokedex/pokedex.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { TeamComponent } from './components/team/team.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { WellcomePageComponent } from './components/wellcome-page/wellcome-page.component';


@NgModule({
  declarations: [
    AppComponent,
    TrainersComponent,
    TrainerItemComponent,
    NavBarComponent,
    PageNotFoundComponent,
    PokemonDetailComponent,
    PokemonListComponent,
    PokedexComponent,
    TeamComponent,
    CardDetailsComponent,
    WellcomePageComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
