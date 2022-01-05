import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';

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
  textAvailabilityInfo = ""
  infoText=""
  fee="123"


  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getBookId();
  }

  getBookId() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
  }

}
