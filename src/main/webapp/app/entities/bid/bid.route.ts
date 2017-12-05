import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { BidComponent } from './bid.component';
import { BidDetailComponent } from './bid-detail.component';
import { BidPopupComponent } from './bid-dialog.component';
import { BidDeletePopupComponent } from './bid-delete-dialog.component';

export const bidRoute: Routes = [
    {
        path: 'bid',
        component: BidComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.bid.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bid/:id',
        component: BidDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.bid.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bidPopupRoute: Routes = [
    {
        path: 'bid-new',
        component: BidPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.bid.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bid/:id/edit',
        component: BidPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.bid.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bid/:id/delete',
        component: BidDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eauctionApp.bid.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
