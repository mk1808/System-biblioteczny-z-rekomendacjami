import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { BookAvailabilityComponent } from 'src/app/book/book-details/book-availability/book-availability.component';
import { I18nService } from 'src/app/core/services/i18n.service';

@Component({
  selector: 'app-lib-book-stats',
  templateUrl: './lib-book-stats.component.html',
  styleUrls: ['./lib-book-stats.component.scss']
})
export class LibBookStatsComponent implements OnInit, OnChanges {
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
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.availability)
  }

  getAvailabilityText(){
    let availObj = {available:this.availability.available, all:this.availability.all};
    let textOnly = this.availability.available>0?LibBookStatsComponent.AVAILABLE:LibBookStatsComponent.NOT_AVAILABLE;
    this.text = this.i18nService.getTranslationWithParams(textOnly, availObj);
  }

  getExtraInfoText(){
    let availObj = {peopleNo:this.availability.peopleWaiting};
    let textOnly = this.availability.peopleWaiting>0?LibBookStatsComponent.RESERVED:LibBookStatsComponent.NOT_RESERVED;
    this.extraInfoText = this.i18nService.getTranslationWithParams(textOnly, availObj);
  }

}
