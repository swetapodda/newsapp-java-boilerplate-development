import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class RouterService {

  constructor(public router: Router, private loc: Location) { }

  routeToDashboard() {
    console.log(' naresh routeToDashboard');
    this.router.navigate(['dashboard']);
  }
  routeToLogin() {
    this.router.navigate(['login']);
  }

  routeToRegisterUser() {
    this.router.navigate(['register']);
  }

  routeToEditNoteView(noteId) {
    this.router.navigate(['dashboard', {
      outlets: {
        noteEditOutlet: ['note', noteId, 'edit']
      }
    }]);
  }

  routeBack() {
    this.loc.back();
  }

  routeToNoteView() {
    this.router.navigate(['dashboard/view/noteview']);
  }

  routeToListView() {
    this.router.navigate(['dashboard/view/listview']);
  }

  routeToIndex() {
    this.router.navigate(['']);
  }

  routeToFavouriteView() {
    this.router.navigate(['favouriteView']);
  }

  routeToRecommendedView() {
    this.router.navigate(['recommendedView']);
  }
}
