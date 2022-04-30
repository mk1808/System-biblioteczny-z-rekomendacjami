import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { I18nService } from './services/i18n.service';
import { I18N_PROVIDERS } from '../shared/i18n';
import { I18NextPipe, I18NEXT_SERVICE } from 'angular-i18next';
import { HttpClientModule } from '@angular/common/http';
import { BooksService } from './services/rest/books.service';
import { BorrowingsService } from './services/rest/borrowings.service';
import { LibrariesService } from './services/rest/libraries.service';
import { ReservationsService } from './services/rest/reservations.service';
import { UsersService } from './services/rest/users.service';
import { DictionaryService } from './services/rest/dictionary.service';
import { FormatterService } from './services/formatter.service';
import { RecommendationsService } from './services/rest/recommendations.service';



@NgModule({
  declarations: [
    //I18nService
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers:[
    {provide:I18nService, deps: [I18NEXT_SERVICE, I18NextPipe]},
    BooksService,
    BorrowingsService,
    LibrariesService,
    ReservationsService,
    UsersService,
    DictionaryService,
    FormatterService,
    RecommendationsService
  ],
  exports:[
    //I18nService
  ]
})
export class CoreModule { }
