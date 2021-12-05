import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { CarouselComponent } from './main/carousel/carousel.component';
import { CarouselElementComponent } from './main/carousel/carousel-element/carousel-element.component';
import { SharedModule } from '../shared/shared.module';
import { MainPagesRoutingModule } from './main-pages-routing.module';
import { IvyCarouselModule } from 'angular-responsive-carousel';




@NgModule({
  declarations: [
    MainComponent,
    CarouselComponent,
    CarouselElementComponent
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
