import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Author, Book, BookFIlter, Genre, Login, Publisher } from 'src/app/core/services/rest/api/api';
import { BooksService } from 'src/app/core/services/rest/books.service';
import { DictionaryService } from 'src/app/core/services/rest/dictionary.service';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.scss']
})
export class BookSearchComponent implements OnInit {
  title: any = "booksSearch";
  titleLabel = "bookAdd.title";
  bookTitle = "title";
  ISBN = "ISBN"
  ISBNLabel = "bookAdd.isbn"
  iconClass = "search"
  text = "search"
  label = "Tytuł"
  btnClass = "full"
  no = 0;
  book = { id: 0, photo: 'https://picsum.photos/200/300', title: 'Lorem ipsum dolor', author: 'Dolores Gibson' }
  books = [this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook(), this.getBook()];
  searchForm: FormGroup = new FormGroup({});
  submitBtnType = "submit";
  genres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);
  authors: BehaviorSubject<Author[]> = new BehaviorSubject<Author[]>([]);
  publishers: BehaviorSubject<Publisher[]> = new BehaviorSubject<Publisher[]>([]);
  booksFiltered: BehaviorSubject<Book[]> = new BehaviorSubject<Book[]>([]);

  constructor(private route: ActivatedRoute, private router: Router, private booksService: BooksService,
    private dictionaryService: DictionaryService) { }

  ngOnInit(): void {
    this.initForm();
    this.updateDictionariesValues()
    this.updateBook();
  }

  getBook() {
    this.no++;
    this.book.id = this.no;
    return this.book;
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

  updateBook(){
    this.booksService.getFiltered(0,0,{})
    this.booksFiltered=this.booksService.searchedBooks;
  }

  initForm() {
    this.searchForm = new FormGroup({
      title: new FormControl(''),
      ISBN: new FormControl(''),
      authorId: new FormControl(''),
      genreId: new FormControl(''),
      publisherId: new FormControl('')
    });
  }

  onSubmit(form: any) {
    console.log(this.searchForm.value)
    let bookFilter: BookFIlter = this.searchForm.value;
    debugger;
    bookFilter.authorId = this.replaceDefaultValue(bookFilter.authorId);
    bookFilter.genreId = this.replaceDefaultValue(bookFilter.genreId);
    bookFilter.publisherId = this.replaceDefaultValue(bookFilter.publisherId);
   
    this.booksService.getFiltered(0, 0, bookFilter);
    this.booksFiltered=this.booksService.searchedBooks;


  }

  getAuthorDisplayedValue(option:any){
   
      return `${option.name} ${option.surname}`
    
  }

  replaceDefaultValue(value:string|undefined){
    return value=="0"?"":value;
  }
}
