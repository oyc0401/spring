# 업데이트
sudo apt update && sudo apt upgrade -y

# 2) 공식 스크립트로 최신 설치
curl -fsSL https://get.docker.com | sudo sh

sudo usermod -aG docker $USER
newgrp docker

# 부팅 시 자동 시작
sudo systemctl enable --now docker

# 4) 확인
docker --version
docker compose version

# nginx 설치 (certbot 할 때 필요함!)
sudo apt install -y nginx
nginx -v

sudo apt install -y certbot python3-certbot-nginx
certbot --version

sudo certbot --nginx -d injob.rokafmail.kr -d injob.rokafmail.kr

sudo certbot renew --dry-run