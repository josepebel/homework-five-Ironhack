import { TeamComponent } from '../app/components/team/team.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { TrainersComponent } from './components/trainers/trainers.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokedexComponent } from './pokedex/pokedex.component';
import { WellcomePageComponent } from './components/wellcome-page/wellcome-page.component';

const routes: Routes = [
  {path: "", component: WellcomePageComponent,},
  {path: "pokedex", component: PokedexComponent},
  {path: "teams", component: TeamComponent},
  {path: "trainers", component: TrainersComponent},
  {path: "**", component: PageNotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
