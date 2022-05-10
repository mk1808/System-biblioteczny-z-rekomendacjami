import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibUserRoutingModule } from './lib-user-routing.module';
import { LibMyAccountComponent } from './lib-my-account/lib-my-account.component';



@NgModule({
  declarations: [
    LibMyAccountComponent
  ],
  imports: [
    CommonModule,
    LibUserRoutingModule
  ]
})
export class LibUserModule { }
