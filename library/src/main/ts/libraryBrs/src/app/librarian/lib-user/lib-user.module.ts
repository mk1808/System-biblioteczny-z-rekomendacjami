import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibUserRoutingModule } from './lib-user-routing.module';
import { LibMyAccountComponent } from './lib-my-account/lib-my-account.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    LibMyAccountComponent
  ],
  imports: [
    CommonModule,
    LibUserRoutingModule,
    SharedModule, 
    ReactiveFormsModule
  ]
})
export class LibUserModule { }
