@echo off

cd C:\Users\%USERNAME%\Documents\GitHub\Tardis-main\Tardis


echo Updating...
git fetch upstream
git merge upstream/master
echo Done.


timeout 3






