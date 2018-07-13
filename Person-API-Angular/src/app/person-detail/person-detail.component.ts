import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Person } from '../person';
import { PersonService } from '../service/person.service';

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.css']
})
export class PersonDetailComponent implements OnInit {
  person: Person;
  id: string = window.location.pathname.replace('/person/', '');

  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private location: Location
  ) { }

  ngOnInit() {
    this.get();
  }

  get(): void {
    /*
      'http://stackoverflow.com/questions/'

      location = {
        href: 'http://stackoverflow/questions/500',
        origin: 'http://stackoverflow.com',
        pathname: '/questions/500'
      }
    */


    // location.pathname = '/person/{id}'

    this.personService.get(this.id)
        .subscribe(person => this.person = person);
  }

  // Back to main page
  toMain(): void {
    this.location.back();
  }

  // changes boxes to edit boxes
  toEdit(): void {
    //
    this.location.go('/person');
  }

  //to delete
  delete(id: string): void {
    this.personService.delete(id);
  }


//
}
