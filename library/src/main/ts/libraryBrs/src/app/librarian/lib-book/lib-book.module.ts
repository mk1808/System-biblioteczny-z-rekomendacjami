import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBookComponent } from './add-book/add-book.component';
import { LibBookRoutingModule } from './lib-book-routing.module';
import { LibBookDetailsComponent } from './lib-book-details/lib-book-details.component';
import { LibBookSearchComponent } from './lib-book-search/lib-book-search.component';
import { AddBookCopyComponent } from './add-book/add-book-copy/add-book-copy.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { LibBookSearchElementComponent } from './lib-book-search/lib-book-search-element/lib-book-search-element.component';
import { LibBookStatsComponent } from './lib-book-details/lib-book-stats/lib-book-stats.component';
import { CoreModule } from 'src/app/core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AddBookComponent,
    LibBookDetailsComponent,
    LibBookSearchComponent,
    AddBookCopyComponent,
    LibBookSearchElementComponent,
    LibBookStatsComponent
  ],
  imports: [
    CommonModule,
    LibBookRoutingModule,
    SharedModule,
    CoreModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class LibBookModule { }
