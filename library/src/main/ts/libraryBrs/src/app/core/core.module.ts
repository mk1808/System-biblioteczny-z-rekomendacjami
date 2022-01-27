import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { I18nService } from './services/i18n.service';
import { I18N_PROVIDERS } from '../shared/i18n';
import { I18NextPipe, I18NEXT_SERVICE } from 'angular-i18next';
import { HttpClientModule } from '@angular/common/http';



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
  ],
  exports:[
    //I18nService
  ]
})
export class CoreModule { }
