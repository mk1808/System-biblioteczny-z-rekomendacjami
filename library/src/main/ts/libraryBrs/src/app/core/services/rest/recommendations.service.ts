import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/recommendations";

@Injectable()
export class RecommendationsService {
  public recommendations: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }


}
