import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

import ProtectedRoute from '~/components/ProtectedRoute';
import DefaultLayout from '~/layouts';

import { CartProvider } from './contexts/cart';
import { UserProvider } from './contexts/user';
import { privateRoutes, publicRoutes } from './routes';

const App = () => {
    return (
        <Router>
            <div className="App">
                <UserProvider>
                    <CartProvider>
                        <Routes>
                            {publicRoutes.map((route, index) => {
                                const Page = route.component;
                                let Layout = DefaultLayout;

                                return (
                                    <Route
                                        key={index}
                                        path={route.path}
                                        element={
                                            <Layout>
                                                <Page />
                                            </Layout>
                                        }
                                    />
                                );
                            })}

                            {privateRoutes.map((route, index) => {
                                const Page = route.component;
                                let Layout = DefaultLayout;

                                return (
                                    <Route
                                        key={index}
                                        path={route.path}
                                        element={
                                            <ProtectedRoute>
                                                <Layout>
                                                    <Page />
                                                </Layout>
                                            </ProtectedRoute>
                                        }
                                    />
                                );
                            })}
                        </Routes>
                    </CartProvider>
                </UserProvider>
            </div>
        </Router>
    );
};

export default App;
