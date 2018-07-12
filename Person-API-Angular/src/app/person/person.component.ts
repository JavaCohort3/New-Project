import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../service/person.service';
import {Routes} from "@angular/router";


@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  person = Person;

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.create(this.person);
  }

  create(person: any): any {
    this.personService.create(person)
      .subscribe(person => this.person = person);
  }


}
