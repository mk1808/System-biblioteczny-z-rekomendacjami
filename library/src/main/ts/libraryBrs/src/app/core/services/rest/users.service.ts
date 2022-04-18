import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { AppUser, Login, Response, UserFilter } from './api/api';
import { RestService } from './rest.service';
const URL = "/api/users";
const AUTH_URL = "api/auth";


var _logged: BehaviorSubject<Boolean> = new BehaviorSubject<Boolean>(false);

@Injectable()
export class UsersService {
  public users: BehaviorSubject<[]> = new BehaviorSubject([]);
  public logged: BehaviorSubject<Boolean> = _logged;
  constructor(private restService: RestService, private router: Router) {
    this.whoAmI().subscribe(response=>{
      this.logged.next(true);
    });
   }

  login(login: Login):Observable<Response<any>> {
    debugger
    this.logged.next(true);
    return this.restService.post(`${AUTH_URL}/authenticate`, login);
  }

  register(user: AppUser) {
    return this.restService.post(`${AUTH_URL}/register`, user);
  }

  whoAmI(): Observable<any> {
    return this.restService.get(`${AUTH_URL}/whoami`);
  }

  logout() {
    return this.restService.post(`${AUTH_URL}/logout`, "").subscribe(response=>{
      console.log(response)
      this.logged.next(false);
      this.router.navigate(['/home']);

    });
  }

  getById(id:string): Observable<Response<any>> {
    return this.restService.get(`${URL}/${id}`);
  }

  update(user:AppUser): Observable<Response<any>> {
    return this.restService.put(`${URL}`, user);
  }

  getFiltered(userFilter:UserFilter): Observable<any> {
    return this.restService.getWithBody(`${URL}/filtered`, userFilter);
  }

  create(user: AppUser): Observable<Response<any>> {
    return this.restService.post(`${URL}`, user);
  }

  updateByAdmin(user:AppUser): Observable<Response<any>> {
    return this.restService.put(`${URL}`, user);
  }

  deactivate(id:string): Observable<Response<any>> {
    return this.restService.put(`${URL}/${id}/deactivate`, "");
  }


}
