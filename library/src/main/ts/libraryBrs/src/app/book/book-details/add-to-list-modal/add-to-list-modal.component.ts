import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Book, Reservation, UserListElement } from 'src/app/core/services/rest/api/api';
import { ReservationsService } from 'src/app/core/services/rest/reservations.service';
import { BooksService } from 'src/app/core/services/rest/books.service';

@Component({
  selector: 'app-add-to-list-modal',
  templateUrl: './add-to-list-modal.component.html',
  styleUrls: ['./add-to-list-modal.component.scss']
})
export class AddToListModalComponent implements OnInit {

  @Input() book: Book={};
  @Input() typeOfList:string="";
  title = "addToList.confirm";
  question="";
  questionFav = "addToListFavourite.confirm.question";
  questionToRead = "addToListToRead.confirm.question";
  userListElement:UserListElement={};

  constructor(private formatterService:FormatterService, public activeModal: NgbActiveModal, public booksService:BooksService) { }

  ngOnInit(): void {
    this.question = this.typeOfList==="f"?this.questionFav:this.questionToRead;
    console.log(this.book)
  }

  onCancel=()=>{
    debugger;
    this.activeModal.dismiss('Cross click');

  }

  onConfirm=()=>{
    this.userListElement.bookId = this.book.id;
    this.userListElement.type = this.typeOfList;
    this.userListElement.userId = "a5edd677-14a9-449b-ab3f-76742762c5f1";
    console.log(this.userListElement)
    this.booksService.createUserListElement(this.userListElement).subscribe(resp=>{
      console.log(resp);
        this.onCancel();
    })
  
  }

  formatAuthors(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayList(list); }
    return "-"
  }
}
