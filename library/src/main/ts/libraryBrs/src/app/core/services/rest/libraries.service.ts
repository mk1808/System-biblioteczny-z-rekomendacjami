import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { RestService } from './rest.service';
const URL = "api/libraries";

@Injectable()
export class LibrariesService {
  public libraries: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }
}
