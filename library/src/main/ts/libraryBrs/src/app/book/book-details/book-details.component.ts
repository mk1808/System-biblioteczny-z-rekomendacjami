import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { switchMap } from 'rxjs';
import { UserListType } from 'src/app/core/enums';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Book, BookAvailability, Response } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { AddToListModalComponent } from './add-to-list-modal/add-to-list-modal.component';
import { RatingModalComponent } from './rating-modal/rating-modal.component';

interface Alert {
  type: string;
  message: string;
}


const ALERTS: Alert[] = [{
  type: 'success',
  message: 'This is an success alert',
}, {
  type: 'info',
  message: 'This is an info alert',
}, {
  type: 'warning',
  message: 'This is a warning alert',
}, {
  type: 'danger',
  message: 'This is a danger alert',
}, {
  type: 'primary',
  message: 'This is a primary alert',
}, {
  type: 'secondary',
  message: 'This is a secondary alert',
}, {
  type: 'light',
  message: 'This is a light alert',
}, {
  type: 'dark',
  message: 'This is a dark alert',
}
];
@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  id: any = 0;
  ids: any;
  title = "bookDetails";
  rulesHeight = "rulesHeight";
  iconClass = "star"
  addClass = "add"
  text = "search"
  btnClass = "full"
  orangeBtnClass = "full orange"
  rightClass = "info"
  leftClass = "info"
  textLogin = "login"
  textRate = "bookDetails.rate"
  textFav = "bookDetails.addToFav"
  textToRead = "bookDetails.addToToRead"
  isAvailable: boolean = false;
  infoText = ""
  fee = "123"
  //availability = { available: 0, all: 5, peopleWaiting: 1 };
  availability: BookAvailability = {};
  comment = {
    username: "Użytkownik 1", content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sed enim in nunc feugiat euismod vitae necnunc. Morbi ac massa ut sem hendrerit",
    rating: 6, date: "05.01.2022r."
  };
  comment5 = {
    username: "Użytkownik 3", content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sed enim in nunc feugiat euismod vitae necnunc. Morbi ac massa ut sem hendrerit",
    rating: 8, date: "30.01.2022r."
  };
  comments = [this.comment, this.comment5]
  book: Book = {};


  alerts: Alert[] = [];


  constructor(private route: ActivatedRoute, private booksService: BooksService, private formatterService: FormatterService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getBookId();
    this.getBook();
    this.getBookAvailability();
    this.reset();

    // this.newq();

  }
  close(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  reset() {
    this.alerts = Array.from(ALERTS);
  }
  getBookId() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
  }

  getBook() {
    console.log(this.id)
    this.booksService.getById(this.id).subscribe((resp: Response<Book>) => {
      if (resp.content) {
        this.book = resp.content;
        console.log(this.book)
      }
    })
  }

  getBookAvailability() {
    this.booksService.getAvailabilityByBookId(this.id).subscribe((resp: Response<BookAvailability>) => {
      if (resp.content) {
        this.availability = resp.content;
        console.log(resp)
      }
    })
  }

  formatAuthors(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayList(list); }
    return "-"
  }

  formatOther(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayListNames(list); }
    return "-";
  }

  newq() {
    let a: Alert = {
      type: 'dark',
      message: 'This is a dark alert',
    };
    this.alerts.push(a);
  }

  addToListFav = () => {
    this.addToList(UserListType.FAV);
  }

  addToListToRead = () => {
    this.addToList(UserListType.TO_READ);
  }

  addToList = (type: UserListType) => {

    const modalRef = this.modalService.open(AddToListModalComponent);
    modalRef.componentInstance.book = this.book;
    modalRef.componentInstance.typeOfList = type;

  }

  giveRating = () => {
    const modalRef = this.modalService.open(RatingModalComponent);
    modalRef.componentInstance.book = this.book;
  }


}
