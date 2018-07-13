import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../service/person.service';
import {Routes} from "@angular/router";
import {Location} from "@angular/common";


@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  person = new Person();
  summit = false;
  constructor(private personService: PersonService, private  location:Location) { }

  ngOnInit() {

  }

  create(): any {
    this.personService.create(this.person)
      .subscribe(person => this.person = person);
  }



  onSubmit() {
    this. summit = true;
    this.create()
  }

  goBack() {
    this.location.back();
  }

}
