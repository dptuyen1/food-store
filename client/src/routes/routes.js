import config from '~/configs';
import { SubLayout } from '~/layouts';
import Cart from '~/pages/Cart';
import Details from '~/pages/Details';

import Home from '~/pages/Home';
import InStore from '~/pages/InStore';
import Login from '~/pages/Login';
import Profile from '~/pages/Profile';
import Register from '~/pages/Register';
import Tracking from '~/pages/Tracking';

const publicRoutes = [
    { path: config.routes.home, component: Home },
    { path: config.routes.login, component: Login },
    { path: config.routes.register, component: Register },
    { path: config.routes.details, component: Details },
    { path: config.routes['in-store'], component: InStore, layout: SubLayout },
];

const privateRoutes = [
    { path: config.routes.profile, component: Profile },
    { path: config.routes.tracking, component: Tracking },
    { path: config.routes.cart, component: Cart },
];

export { privateRoutes, publicRoutes };
