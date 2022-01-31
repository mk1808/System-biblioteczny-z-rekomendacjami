import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { FormatterService } from 'src/app/core/services/formatter.service';
import { Book, Response } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';

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
  availability = { available: 0, all: 5, peopleWaiting: 1 };
  comment = {
    username: "Użytkownik 1", content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sed enim in nunc feugiat euismod vitae necnunc. Morbi ac massa ut sem hendrerit",
    rating: 6, date: "05.01.2022r."
  };
  comments = [this.comment, this.comment, this.comment, this.comment]
  book: Book = {};


  constructor(private route: ActivatedRoute, private booksService: BooksService, private formatterService: FormatterService) { }

  ngOnInit(): void {
    this.getBookId();
    this.getBook();

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

  formatAuthors(list: Object[] | undefined) {
    if (list) { return this.formatterService.displayList(list); }
    return "-"
  }

  formatOther(list: Object[] | undefined) {
    debugger;
    if (list) { return this.formatterService.displayListNames(list); }
    return "-";
  }




}
