import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecommendationListComponent } from './recommendation-list/recommendation-list.component';

const routes: Routes = [
  { path: 'all', component: RecommendationListComponent },
  { path: '**',  component: RecommendationListComponent }

];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class RecommendationRoutingModule { }
