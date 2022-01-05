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
  isAvailable:boolean=false;
  infoText=""
  fee="123"
  availability={available:0, all:5, peopleWaiting:1};
  comment = {username:"Użytkownik 1", content:"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sed enim in nunc feugiat euismod vitae necnunc. Morbi ac massa ut sem hendrerit", 
  rating:6, date:"05.01.2022r."};
  comments = [this.comment, this.comment, this.comment, this.comment]


  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getBookId();
  }

  getBookId() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
  }


}
