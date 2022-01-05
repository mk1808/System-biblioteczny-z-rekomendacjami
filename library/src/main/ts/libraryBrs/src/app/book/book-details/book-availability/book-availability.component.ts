import { Component, Inject, Input, OnInit } from '@angular/core';
import { I18NextPipe, I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-book-availability',
  templateUrl: './book-availability.component.html',
  styleUrls: ['./book-availability.component.scss']
})
export class BookAvailabilityComponent implements OnInit {
  @Input() availability:any;
  static readonly AVAILABLE="bookDetails.availability.available";
  static readonly NOT_AVAILABLE="bookDetails.availability.notAvailable";
  
  text="";
  other = this.i18nService.getTranslation("bookDetails.rate");
  other2= this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  constructor(private i18nService: I18nService) { }

  ngOnInit(): void {
    this.getAvailabilityText();
   // this.other=this.i18nService.getTranslation("bookDetails.rate");
   // this.other2=this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  }

  getAvailabilityText(){
    let availObj = {available:this.availability.available, all:this.availability.all};
    let textOnly = this.availability.available>0?BookAvailabilityComponent.AVAILABLE:BookAvailabilityComponent.NOT_AVAILABLE;
    this.text = this.i18nService.getTranslationWithParams(textOnly, availObj);
    

  }



}
