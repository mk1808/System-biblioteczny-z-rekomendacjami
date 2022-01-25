import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './add-book/add-book.component';
import { LibBookDetailsComponent } from './lib-book-details/lib-book-details.component';
import { LibBookSearchComponent } from './lib-book-search/lib-book-search.component';


const routes: Routes = [

  { path: 'new', component: AddBookComponent },
  { path: 'search', component: LibBookSearchComponent },
  { path: 'details', component: LibBookDetailsComponent },
  
  { path: '**', component: AddBookComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LibBookRoutingModule { }
