import { Component, Inject, Input, OnInit } from '@angular/core';
import { I18NextPipe, I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-book-availability',
  templateUrl: './book-availability.component.html',
  styleUrls: ['./book-availability.component.scss']
})
export class BookAvailabilityComponent implements OnInit {
  @Input() infoText:string="";
  other = this.i18nService.getTranslation("bookDetails.rate");
  other2= this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  constructor(private i18nService: I18nService) { }
  
nazwa=""

  ngOnInit(): void {
    this.nazwa = this.i18nService.getTranslation("bookDetails.rate")
   // this.other=this.i18nService.getTranslation("bookDetails.rate");
   // this.other2=this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  }





}
