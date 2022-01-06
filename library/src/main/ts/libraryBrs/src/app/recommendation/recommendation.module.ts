import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecommendationListComponent } from './recommendation-list/recommendation-list.component';
import { RecommendationElementComponent } from './recommendation-list/recommendation-element/recommendation-element.component';
import { RecommendationRoutingModule } from './recommendation-routing.module';



@NgModule({
  declarations: [
    RecommendationListComponent,
    RecommendationElementComponent
  ],
  imports: [
    CommonModule,
    RecommendationRoutingModule
  ]
})
export class RecommendationModule { }
