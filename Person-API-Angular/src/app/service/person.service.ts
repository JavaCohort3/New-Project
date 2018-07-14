import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/internal/Observable";

@Injectable()
export class PersonService {
  public api = 'http://localhost:8080/people';

  constructor(private http: HttpClient) {}

  // create method
  create(person: any): Observable<any> {
    return this.http.post(this.api,person );
  }

  // get method
  get(id: number):Observable<any>{
    return this.http.get(`${this.api}/${id}`);
  }

  // get all method
  getAll(): Observable<any> {
    return this.http.get(this.api);
  }

  // put method
  update(person: any): Observable<any> {
    return this.http.put(`${this.api}/${person.id}`, person);
  }

  // delete method
  delete(id: number): Observable<any> {
   return this.http.delete(`${this.api}/${id}`);
  }
}
