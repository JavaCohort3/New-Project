import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import { Location } from '@angular/common';

import { Person } from '../person';
import { PersonService } from '../service/person.service';
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.css']
})
export class PersonDetailComponent implements OnInit {
  person: Person = new Person();



  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private location: Location
  ) { }

  ngOnInit() {
    this.route.params.pipe(
      switchMap((params: Params) => this.personService.get(+params['id']))
    ).subscribe(newPerson => this.person = newPerson)
  }

  // Back to main page
  toMain(): void {
    this.location.back();
  }

  // changes boxes to edit boxes


  //to delete
  delete(): void {
    this.personService.delete(this.person.id).subscribe(() => this.toMain())
  }


//
}
