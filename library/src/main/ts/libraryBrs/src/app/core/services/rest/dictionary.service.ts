import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Author, Genre, KeyWord, Publisher, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/dictionaries";

@Injectable()
export class DictionaryService {
  public authors: BehaviorSubject<Author[]> = new BehaviorSubject<Author[]>([]);
  public genres: BehaviorSubject<Genre[]> = new BehaviorSubject<Genre[]>([]);
  public publishers: BehaviorSubject<Publisher[]> = new BehaviorSubject<Publisher[]>([]);
  public keywords: BehaviorSubject<KeyWord[]> = new BehaviorSubject<KeyWord[]>([]);

  constructor(private restService: RestService) { }

  getAuthors(): any {
    return this.restService.get<Response<Author[]>>(`${URL}/authors`).subscribe((resp:Response<Author[]>) => {
      if(resp.content){
         this.authors.next(resp.content);
      }
     
    })
  }

  getGenres(): any {
    return this.restService.get<Response<Genre[]>>(`${URL}/genres`).subscribe((resp:Response<Genre[]>) => {
      if(resp.content){
         this.genres.next(resp.content);
      }
     
    })
  }

  getPublishers(): any {
    return this.restService.get<Response<Publisher[]>>(`${URL}/publishers`).subscribe((resp:Response<Publisher[]>) => {
      if(resp.content){
         this.publishers.next(resp.content);
      }
     
    })
  }

  getKeyWords(): any {
    return this.restService.get<Response<Publisher[]>>(`${URL}/keywords`).subscribe((resp:Response<KeyWord[]>) => {
      if(resp.content){
         this.keywords.next(resp.content);
      }
    })
  }
}
