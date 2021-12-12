import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { CarouselComponent } from './main/carousel/carousel.component';
import { CarouselElementComponent } from './main/carousel/carousel-element/carousel-element.component';
import { SharedModule } from '../shared/shared.module';
import { MainPagesRoutingModule } from './main-pages-routing.module';
import { IvyCarouselModule } from 'angular-responsive-carousel';
import { ContactComponent } from './contact/contact.component';
import { RulesComponent } from './rules/rules.component';
import { MapComponent } from './contact/map/map.component';





@NgModule({
  declarations: [
    MainComponent,
    CarouselComponent,
    CarouselElementComponent,
    ContactComponent,
    RulesComponent,
    MapComponent
  ],
  imports: [
    CommonModule,
    IvyCarouselModule,
    SharedModule,
    MainPagesRoutingModule
  ],
  exports: [
    MainComponent

  ]
})
export class MainPagesModule { }
