import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Author, Genre, Publisher } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { DictionaryService } from 'src/app/core/services/rest/dictionary.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {
  title = 'addBook';
  rulesHeight = "rulesHeight";
  isbnInput="bookAdd.isbn";
  titleInput="bookAdd.title";
  authorInput="bookAddauthor";
  genreInput="bookAddGenre"
  publisherInput = "bookAddPublisher";
  yearInput = "bookAddYear";
  seriesInput = "bookAddSeries";
  originalTitleInput = "bookAddOriginalTitle";
  descriptionInput="bookDescInput";
  btnClassSmall="small"
  btnClass="full"
  mailInput="mail";
  addBnt=""
  iconClass="add";
  passwordInput="password"
  inputClass="log-reg";
 // iconClass="search"
  text="search"
  label="label"
  label1="label1"
  
  
  rightClass="info"
  leftClass="info"
  textLogin="login"
  textRegister="goToRegister"
  textOpen="open"
  prolongText="Zatwierdź"
  cancelText="Anuluj"
  confirmBtnClass="full orange"
  cancelClass="full gray"
  bookAddForm:FormGroup= this.initForm();

  genres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);
  authors: BehaviorSubject<Author[]> = new BehaviorSubject<Author[]>([]);
  publishers: BehaviorSubject<Publisher[]> = new BehaviorSubject<Publisher[]>([]);

  constructor(private route: ActivatedRoute, private router: Router, private booksService: BooksService,
    private dictionaryService: DictionaryService, private fb:FormBuilder) { }

  ngOnInit(): void {
    this.updateDictionariesValues()
  }

  updateDictionariesValues() {
    this.dictionaryService.getAuthors();
    this.dictionaryService.getGenres();
    this.dictionaryService.getPublishers();
    this.authors = this.dictionaryService.authors;
    this.genres = this.dictionaryService.genres;
    this.publishers = this.dictionaryService.publishers;
    console.log(this.authors)

    console.log(this.publishers)
    console.log(this.genres)

  }

  initForm() {
    return this.fb.group({
      ISBN: "",
      title: "",
      publicationYear: "",
      originalTitle: "",
      descrpition: "",
      photo: "",
      publisherId: "",
      publisherName: "",
      authors: [],
      genres: [],
      series: [],
      keyWords: []
    })
  }

  onCancel(){

  }

  onConfirm(){

  }

}
