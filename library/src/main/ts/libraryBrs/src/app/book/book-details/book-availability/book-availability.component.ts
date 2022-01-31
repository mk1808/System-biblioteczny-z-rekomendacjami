import { Component, Inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { I18NextPipe, I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import { I18nService } from 'src/app/core/services/i18n.service';
import { BookAvailability } from 'src/app/core/services/rest/api/api';

@Component({
  selector: 'app-book-availability',
  templateUrl: './book-availability.component.html',
  styleUrls: ['./book-availability.component.scss']
})
export class BookAvailabilityComponent implements OnInit, OnChanges {
  @Input() availability: BookAvailability = {};
  static readonly AVAILABLE = "bookDetails.availability.available";
  static readonly NOT_AVAILABLE = "bookDetails.availability.notAvailable";
  static readonly RESERVED = "bookDetails.availability.reserved"
  static readonly NOT_RESERVED = "bookDetails.availability.notReserved"
  reserveText = "bookDetails.availability.makeReservation";
  btnClass = "full"
  text = "";
  extraInfoText = "";
  constructor(private i18nService: I18nService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.availability)
    debugger;
    this.getAvailabilityText();
    this.getExtraInfoText();
  }

  ngOnInit(): void {
    this.getAvailabilityText();
    this.getExtraInfoText();
  }

  getAvailabilityText() {
    let availObj = { available: this.availability.available, all: this.availability.allBooks };
    let textOnly = this.availability.available && this.availability.available > 0 ? BookAvailabilityComponent.AVAILABLE : BookAvailabilityComponent.NOT_AVAILABLE;
    this.text = this.i18nService.getTranslationWithParams(textOnly, this.availability);
  }

  getExtraInfoText() {
    let availObj = { peopleNo: this.availability.numberOfReservations };
    let textOnly = this.availability.numberOfReservations && this.availability.numberOfReservations > 0 ? BookAvailabilityComponent.RESERVED : BookAvailabilityComponent.NOT_RESERVED;
    this.extraInfoText = this.i18nService.getTranslationWithParams(textOnly, this.availability);
  }

  makeReservation() {
    console.log("reservation!")
  }



}
