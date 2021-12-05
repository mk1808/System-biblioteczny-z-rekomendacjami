import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main/main.component';
import { IvyCarouselModule } from 'angular-responsive-carousel';
import { CarouselComponent } from './main/carousel/carousel.component';




@NgModule({
  declarations: [
    
  
    MainComponent,
            CarouselComponent
  ],
  imports: [
    CommonModule,
    IvyCarouselModule
  ],
  exports: [
    MainComponent

  ]
})
export class MainPagesModule { }
