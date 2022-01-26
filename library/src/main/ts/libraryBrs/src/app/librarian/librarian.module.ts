import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibrarianRoutingModule } from './librarian-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CoreModule } from '../core/core.module';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    LibrarianRoutingModule,
    SharedModule,
    CoreModule
  ]
})
export class LibrarianModule { }
