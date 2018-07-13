import { Component, OnInit } from '@angular/core';

import { Person } from "../person";
import { PersonService } from "../service/person.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  people: Person[] = [];

  constructor(private personService: PersonService) {}

  ngOnInit() { this.getPeople(); }

  createPerson(): void {
    location.href = '/person';
  }

  getPeople(): void {
    this.personService.getAll().subscribe(people => this.people = people);
  }
}
