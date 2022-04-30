import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Recommendation, Response } from './api/api';
import { RestService } from './rest.service';
const URL = "api/recommendations";

@Injectable()
export class RecommendationsService {
  public recommendations: BehaviorSubject<[]> = new BehaviorSubject([]);

  constructor(private restService: RestService) { }

  getByUserId(userId:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/user/${userId}?pageNo=1&pageSize=1`);
  }

  updateInfo(recommendation:Recommendation): Observable<Response<any>> {
    return this.restService.patch(`${URL}`, recommendation);
  }
}
