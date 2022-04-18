import { Component, Inject, OnInit } from '@angular/core';
import { NgbAccordion } from '@ng-bootstrap/ng-bootstrap';
import { I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';
import i18next from 'i18next';
import { UserListType } from 'src/app/core/enums';
import { Author } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
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
  pastBorrowings:any;
  reservations:any;
  toReadBooks:any;
  favBooks:any;
  constructor( @Inject(I18NEXT_SERVICE) private i18NextService: ITranslationService, private borrowingsService:BorrowingsService,
  private reservationsService:ReservationsService, private booksService:BooksService) { }

  ngOnInit(): void {
    this.updateBorrowings()
    this.updateReservations()
    this.updatePastBorrowings()
    this.getFav()
    this.getToRead()

    this.borrowings = this.borrowingsService.borrowings;
    this.reservations = this.reservationsService.reservations;
    this.pastBorrowings = this.borrowingsService.pastBorrowings;
    console.log(this.pastBorrowings)
    
    
  }

  updateBorrowings(){
    this.borrowingsService.getByUserId("a5edd677-14a9-449b-ab3f-76742762c5f1")
  }

  updateReservations(){
    this.reservationsService.getByUserId("a5edd677-14a9-449b-ab3f-76742762c5f1")
  }

  updatePastBorrowings(){
    this.borrowingsService.getPastByUserId("a5edd677-14a9-449b-ab3f-76742762c5f1")
  }

  getFav(){
    this.booksService.getUserListElementByUserAndType("a5edd677-14a9-449b-ab3f-76742762c5f1", UserListType.FAV).subscribe(reponse=>{
      console.log(reponse)
      this.favBooks = reponse.content;
    })
  }

  getToRead(){
    this.booksService.getUserListElementByUserAndType("a5edd677-14a9-449b-ab3f-76742762c5f1", UserListType.TO_READ).subscribe(reponse=>{
      console.log(reponse)
      this.toReadBooks = reponse.content;
    })
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
