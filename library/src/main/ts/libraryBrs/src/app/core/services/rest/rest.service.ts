import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  public get<T>(url: string) {
    return this.http.get<T>(url).pipe(
      catchError(this.handleError)
    );
  }

  public getWithParams<T>(url: string, paramName:string, paramContent:any) {
    let params= new HttpParams().set(paramName,encodeURIComponent(JSON.stringify(paramContent)) )
    console.log(params)
    return this.http.get<T>(url, {params:params}).pipe(
      catchError(this.handleError)
    );
  }

  public delete<T>(url: string) {
    return this.http.delete<T>(url).pipe(
      catchError(this.handleError)
    );
  }

  public post(url: string, body: any) {
    return this.http.post(url, body).pipe(
      catchError(this.handleError)
    );
  }

  public put(url: string, body: any) {
    return this.http.put(url, body).pipe(
      catchError(this.handleError)
    );
  }

  public patch(url: string, body: any) {
    return this.http.patch(url, body).pipe(
      catchError(this.handleError)
    );
  }

  public getWithBody<T>(url: string, body: any) {
    return this.http.get<T>(url, body).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.log("Error: ", error)
    return [];
  }
}
