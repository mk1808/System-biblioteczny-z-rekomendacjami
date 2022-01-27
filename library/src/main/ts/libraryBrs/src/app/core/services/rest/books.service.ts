import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RestService } from './rest.service';
const URL = "api/books";

@Injectable()
export class BooksService {
  public books: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }


}

