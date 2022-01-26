import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { I18NEXT_SERVICE, ITranslationService } from 'angular-i18next';

@Component({
  selector: 'app-lib-book-details',
  templateUrl: './lib-book-details.component.html',
  styleUrls: ['./lib-book-details.component.scss']
})
export class LibBookDetailsComponent implements OnInit {

  id: any = 0;
  ids: any;
  title = "bookDetails";
  rulesHeight = "rulesHeight";
  iconClass = "star"
  addClass = "add"
  downloadClass="download"
  editClass="edit"
  text = "search"
  btnClass = "full"
  orangeBtnClass = "full orange"
  rightClass = "info"
  leftClass = "info"
  textLogin = "login"
  textRate = "bookDetails.rate"
  textFav = "bookDetails.addToFav"
  textToRead = "bookDetails.addToToRead"
  textAdd="bookDetails.addCopy"
  textEdit="bookDetails.edit"
  textDownload="bookDetails.download"
  isAvailable:boolean=false;
  infoText=""
  fee="123"
  availability={available:0, all:5, peopleWaiting:1};
  comment = {username:"Użytkownik 1", content:"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sed enim in nunc feugiat euismod vitae necnunc. Morbi ac massa ut sem hendrerit", 
  rating:6, date:"05.01.2022r."};
  comments = [this.comment, this.comment, this.comment, this.comment]
 

  accHeaders=this.getAccorditionHeaders();
  headers=this.getHeaders();
  text1=""
  deleteText=this.getTranslation("booklist.table.delete")
  prolongText=this.getTranslation("booklist.table.prolong")
  resignText=this.getTranslation("booklist.table.resign")


  constructor(private route: ActivatedRoute, @Inject(I18NEXT_SERVICE) private i18NextService: ITranslationService) { }

  ngOnInit(): void {
    this.getBookId();
  }

  getBookId() {
    this.id = this.route.snapshot.paramMap.get('id');
    console.log(this.id)
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
