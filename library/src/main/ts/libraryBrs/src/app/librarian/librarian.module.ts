import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibLoginComponent } from './lib-login/lib-login.component';
import { LibrarianRoutingModule } from './librarian-routing.module';



@NgModule({
  declarations: [
    LibLoginComponent
  ],
  imports: [
    CommonModule,
    LibrarianRoutingModule
  ]
})
export class LibrarianModule { }
