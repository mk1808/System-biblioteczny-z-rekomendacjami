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
  static readonly RESERVED="bookDetails.availability.reserved"
  static readonly NOT_RESERVED="bookDetails.availability.notReserved"
  reserveText="bookDetails.availability.makeReservation";
  btnClass="full"
  text="";
  extraInfoText="";
  other = this.i18nService.getTranslation("bookDetails.rate");
  other2= this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  constructor(private i18nService: I18nService) { }

  ngOnInit(): void {
    this.getAvailabilityText();
    this.getExtraInfoText();
    
   // this.other=this.i18nService.getTranslation("bookDetails.rate");
   // this.other2=this.i18nService.getTranslationWithParams("bookDetails.availability.available", {"fee":"aqw"})
  }

  getAvailabilityText(){
    let availObj = {available:this.availability.available, all:this.availability.all};
    let textOnly = this.availability.available>0?BookAvailabilityComponent.AVAILABLE:BookAvailabilityComponent.NOT_AVAILABLE;
    this.text = this.i18nService.getTranslationWithParams(textOnly, availObj);
  }

  getExtraInfoText(){
    let availObj = {peopleNo:this.availability.peopleWaiting};
    let textOnly = this.availability.peopleWaiting>0?BookAvailabilityComponent.RESERVED:BookAvailabilityComponent.NOT_RESERVED;
    this.extraInfoText = this.i18nService.getTranslationWithParams(textOnly, availObj);
  }

  makeReservation() {
    console.log("reservation!")
  }



}
