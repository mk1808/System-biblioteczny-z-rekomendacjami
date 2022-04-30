import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecommendationListComponent } from './recommendation-list/recommendation-list.component';
import { RecommendationElementComponent } from './recommendation-list/recommendation-element/recommendation-element.component';
import { RecommendationRoutingModule } from './recommendation-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CoreModule } from '../core/core.module';



@NgModule({
  declarations: [
    RecommendationListComponent,
    RecommendationElementComponent
  ],
  imports: [
    CommonModule,
    RecommendationRoutingModule,
    SharedModule,
    CoreModule
  ],
  providers:[
    CoreModule
  ]
})
export class RecommendationModule { }
