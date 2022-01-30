import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Author, BookFIlter, Genre, Login, Publisher } from 'src/app/core/services/rest/api/api';
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

  constructor(private route: ActivatedRoute, private router: Router, private booksService: BooksService,
    private dictionaryService: DictionaryService) { }

  ngOnInit(): void {
    this.initForm();
    this.updateDictionariesValues()
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
    this.authors.getValue();
    this.genres = this.dictionaryService.genres;
    this.genres.getValue();
    this.publishers = this.dictionaryService.publishers;
    this.publishers.getValue();
    console.log(this.authors)

    console.log(this.publishers)
    console.log(this.genres)

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
    this.booksService.getFiltered(0, 0, bookFilter).subscribe(resp => {
      //this.router.navigate(['/user/myAccount']);
      console.log(resp)
    })


  }
}
