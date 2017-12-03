import { Component, OnInit, Input } from '@angular/core';

import { User, UserService } from '../../../shared';

@Component({
    selector: 'app-user-account-label',
    templateUrl: './user-account-label.component.html'
})
export class UserAccountLabelComponent implements OnInit {
    @Input()
    id: number;
    user: User;

    constructor(
        private userService: UserService
    ) { }

    ngOnInit() {
        this.load(this.id);
    }

    load(id) {
        this.userService.findById(id).subscribe((user) => {
            this.user = user;
        });
    }
    previousState() {
        window.history.back();
    }
}
