import { Component, Inject, OnInit } from '@angular/core';
import { NgbAccordion } from '@ng-bootstrap/ng-bootstrap';
import { I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import i18next from 'i18next';
import { Author } from 'src/app/core/services/rest/api/api';
import { BorrowingsService } from 'src/app/core/services/rest/borrowings.service';
import { ReservationsService } from 'src/app/core/services/rest/reservations.service';
@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {
  title="myBooks";
  rulesHeight = "rulesHeight";
  accHeaders=this.getAccorditionHeaders();
  headers=this.getHeaders();
  text=""
  deleteText=this.getTranslation("booklist.table.delete")
  prolongText=this.getTranslation("booklist.table.prolong")
  resignText=this.getTranslation("booklist.table.resign")
  btnClass="full"
  borrowings:any;
  reservations:any;
  constructor( @Inject(I18NEXT_SERVICE) private i18NextService: ITranslationService, private borrowingsService:BorrowingsService,
  private reservationsService:ReservationsService) { }

  ngOnInit(): void {
    this.updateBorrowings()
    this.updateReservations()
    this.borrowings = this.borrowingsService.borrowings;
    this.reservations = this.reservationsService.reservations;
    console.log(this.reservations)
    
    
  }

  updateBorrowings(){
    this.borrowingsService.getByUserId("a5edd677-14a9-449b-ab3f-76742762c5f1")
  }

  updateReservations(){
    this.reservationsService.getByUserId("a5edd677-14a9-449b-ab3f-76742762c5f1")
  }

  formatAuthors(authors:Author[]){
    let displayed="";
    authors.forEach(x=>{displayed+=`${x.name} ${x.surname}, `})
    displayed=displayed.substring(0, displayed.length-2);
    return displayed;
  }

  getTranslation(key:String){
    return this.i18NextService.getResource("pl", "translation", key, "");
  }

  getAccorditionHeaders() {
    return {
      borrowed: this.getTranslation("booklist.borrowed"), 
      borrowedInPast: this.getTranslation("booklist.borrowedInPast"),
      reserved: this.getTranslation("booklist.reserved"), 
      toReadList: this.getTranslation("booklist.toReadList"), 
      favList: this.getTranslation("booklist.favList"), 
      rated: this.getTranslation("booklist.rated")
    }
  }
  getHeaders() {
    return {
      borrowed: this.getTranslation("booklist.borrowed"), 
      borrowedInPast: this.getTranslation("booklist.borrowedInPast"),
      reserved: this.getTranslation("booklist.reserved"), 
      toReadList: this.getTranslation("booklist.toReadList"), 
      favList: this.getTranslation("booklist.favList"), 
      rated: this.getTranslation("booklist.rated")
    }
  }

}
